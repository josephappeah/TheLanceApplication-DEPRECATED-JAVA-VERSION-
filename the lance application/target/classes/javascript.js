/*

    author: Joseph Appeah
    project: The Lance application js code
    last update: 07/22/2016

*/

/*===================================
            Variables
====================================*/
var imagelinksmap = new Map(), imagesselected = [], array = [], file, result, selectedimages, descriptionHeightFlag = false;
$('#fileuploadbutton').popover();



/*===================================
            Functions
====================================*/
function getSelectedImageLinks(image)
{
    if (imagelinksmap.has(image.src))
    {
        imagelinksmap.delete(image.src);
        imagelinksmap.removeAttribute('class','selected-image');
    }
        else if (!imagelinksmap.has(image.src))
    {
        imagelinksmap.set(image.src,image.src);
        image.setAttribute('class','selected-image');
    }    
    displayDoneButton();
    imagesselected.push(image.src);
}


$(document).ready(function()
{
    // resize fonts on the page
    resizeFontById("firstAside");
    resizeFontById("secondAside");
    resizeFontById("search-form");
    resizeFontById("info-textarea");
    resizeFontById("description-heading-1");
    resizeFontById("description-message-1");
    resizeFontById("description-heading-2");
    resizeFontById("description-message-2");
    resizeFontById("description-heading-3");
    resizeFontById("description-message-3");
    resizeFontById("description-message-3");

    resizeFontById("join-us-name");
    resizeFontById("join-us-email");
    resizeFontById("join-us-header-message");
    resizeFontById("join-us-favorite-project");

    resizeFontById("contact-send-button");
    resizeFontById("headerMessage");
    resizeFontById("headerMessage_style");
    resizeFontById("headerButton");
    resizeFontById("getsearchbutton");
    resizeFontById("fileuploadbutton");   
    
    // make navbar appear on scroll
    actionOnOffset(70, function(){$('.navbar').addClass('scrollnav')}, 
        function(){$('.navbar').removeClass('scrollnav')});

    // make header button jump
    setInterval(function()
    { 
            $("#headerButton").addClass('animated bounce');
        setTimeout(function()
        { 
            $("#headerButton").removeClass('animated bounce');
        },2000);
    },6000);

    // Display appropriate button based on text area input
    $('#search-form').on("keyup", function() 
    {
        var formContents = $(this).val(); 
        if ( formContents === '' ) 
        {
            displayUploadButton();
        }
        if ( formContents !== '' ) 
        {
            displayGenerateButton();
        } 
    });

    // Resize description section height per screen size
    $(window).resize(function()
    {
        var screensize = document.documentElement.clientWidth;
        if(screensize  < 1280 && descriptionHeightFlag)
        {
            document.getElementById("description").style.height = '90%';
        } else  if(screensize  < 1280 && descriptionHeightFlag)
        {
            document.getElementById("description").style.height = '140%';  
        }
    });

    $('#headerButton').click(function(){
        var scrollPos = $("#"+"serviceinfo").offset().top - 55;
        $('html, body').animate({scrollTop : scrollPos},'slow');
        return false;
    });

    $('#navbar-link-2').click(function(){
        var scrollPos = $("#"+"serviceinfo").offset().top - 55;
        $('html, body').animate({scrollTop : scrollPos},'slow');
        return false;
    });

    $('#navbar-link-1').click(function(){
        $('html, body').animate({scrollTop : 0},'slow');
        return false;
    });

    $('#navbar-link-3').click(function(){
        var scrollPos = $("#"+"contact-info").offset().top + 100;
        $('html, body').animate({scrollTop : scrollPos},'slow');
        return false;
    });

    $('#navbar-link-1').mouseenter(function () {
        animateById( 'navbar-link-1', 'jello');
    });

    $('#navbar-link-2').mouseenter(function () {
        animateById( 'navbar-link-2', 'jello');
    });

    $('#navbar-link-3').mouseenter(function () {
        animateById( 'navbar-link-3', 'jello');
    });



});


function generateButtonFunctionality()
{
    if ( document.getElementById('getsearchbutton').innerHTML === 'Done' )
    {
        document.getElementById('info-textarea').value = document.getElementById('search-form').value;
        document.getElementById('contact').className = 'animated zoomIn';
        setTimeout(function()
        {   
            document.getElementById('contact').className ='';
        },2000);
        setTimeout(function()
        {  
            var scrollPos = $("#"+"contact").offset().top - 55; 
            $('html, body').animate({scrollTop: scrollPos},'slow');
            return false;
        },1000);
    }
    if ( document.getElementById('getsearchbutton').innerHTML === 'Reload Images' || 
         document.getElementById('getsearchbutton').innerHTML === 'Generate Images')
    {
        document.getElementById("carousel_div").className = "hidden-lg hidden-md";
        var query = encodeURI(document.getElementById('search-form').value);
        getHttpRequest("GET","/the_lance_application/imagesearch?query=" + encodeURI(query), null, null);
    }
}


function createCORSRequest(method, url) 
{  
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) 
    {
        xhr.open(method, url, false);
    } else if (typeof XDomainRequest != "undefined") 
    {
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else 
    {
        xhr = null;
    }
    return xhr;
}


function getHttpRequest(method, theUrl, body, type)
{
    var xhr = createCORSRequest(method,theUrl);
    if(xhr)
    {
        xhr.onload = function(){
            if(type == 'cv'){
                document.getElementById('search-form').value = xhr.responseText;
            }else{
                result = xhr.responseText;
                getSearch(xhr.responseText);
            }
        }
    }
    xhr.send(body);
}


function selectUploadFile()
{
    document.getElementById('fileinput').click(); 
    return false;
}


function appendImages()
{
    descriptionHeightFlag = true;
    var screensize = document.documentElement.clientWidth;
    if(screensize < 1280)
    {
        document.getElementById("description").style.height = '90%';
    }else{
        document.getElementById("description").style.height = '140%';  
    }

    document.getElementById("description_container").style.height = '100%'; 
    document.getElementById("topAppendedImages").style.height = '30%'; 
    document.getElementById("bottomAppendedImages").style.height = '30%'; 
    document.getElementById("description_and_sideimage").style.height = '30%'; 
    document.getElementById("describe_buttons").style.height = '10%'; 
}


function deleteOldImages(){
    while (document.getElementById("sideAppendedImages").firstChild) 
    {
        document.getElementById("sideAppendedImages").removeChild(document.getElementById("sideAppendedImages").firstChild);
    }

    while (document.getElementById("carousel_div").firstChild) 
    {
        document.getElementById("carousel_div").removeChild(document.getElementById("carousel_div").firstChild);
    }

    while (document.getElementById("topAppendedImages").firstChild) 
    {
        document.getElementById("topAppendedImages").removeChild(document.getElementById("topAppendedImages").firstChild);
        document.getElementById("bottomAppendedImages").removeChild(document.getElementById("bottomAppendedImages").firstChild);
    }
}


function generateURL(){
    return array[Math.floor((Math.random() * array.length + 1))];
}


function displayGenerateButton(){
    for(i =1;i>=0;i-=0.0001)
    {
        document.getElementById("fileuploadbutton").style.opacity = i;
    }
    
    document.getElementById("getsearchbutton_container").className = "container-fluid";
    document.getElementById("uploadimagebutton_container").className ="container-fluid hidden";
    document.getElementById("fileuploadbutton").style.visibility = "hidden";
    document.getElementById("getsearchbutton").style.visibility = "visible";

    for(i =0;i<=1;i+=0.0001)
    {
        document.getElementById("getsearchbutton").style.opacity = i;
    }
}

function displayUploadButton(){
    for(i = 1; i>=0; i-=0.0001)
    {
        document.getElementById("getsearchbutton").style.opacity = i;
    }
    
    document.getElementById("uploadimagebutton_container").className ="container-fluid";
    document.getElementById("getsearchbutton_container").className = "container-fluid hidden";
    document.getElementById("getsearchbutton").style.visibility = "hidden";
    document.getElementById("fileuploadbutton").style.visibility = "visible";

    for(i =0;i<=1;i+=0.0001)
    {
        document.getElementById("fileuploadbutton").style.opacity = i;
    }
}

function carousel()
{
    var box = document.querySelector('.carouselbox');
    var next = box.querySelector('.next');
    var prev = box.querySelector('.prev');

    var counter = 0;
    var items = box.querySelectorAll('.content li');
    var amount = items.length;
    var current = items[0];
    box.classList.add('active');

    function navigate(direction) 
    {

        current.classList.remove('current');
    
        counter = (counter + direction) % amount;
        counter = counter < 0 ? amount - 1 : counter;

        current = items[counter];
        current.classList.add('current');
    }

  next.addEventListener('click', function(ev) {
    navigate(1);
  });

  prev.addEventListener('click', function(ev) {
    navigate(-1);
  });
  navigate(0);
}


function displayDoneButton(){

    var outerdiv = document.getElementById("description_container").childNodes;
    var doneFlag = false;

    for(var i = 0; i < outerdiv.length; i++) 
    {
        console.log(outerdiv[i].id);
        var innerdiv = outerdiv[i].childNodes;

        for(var j = 0; j < innerdiv.length; j++ )
        {
            if( innerdiv[j].className != undefined && innerdiv[j].className.includes('selected-image'))
            {
                    doneFlag = true; 
            }
            var innerinnerdiv = innerdiv[j].childNodes;

            for(var k = 0; k < innerinnerdiv.length; k++ )
            {
                var innerinnerinnerdiv = innerinnerdiv[k].childNodes;
                if(innerinnerdiv[k].className != undefined && innerinnerdiv[k].className.includes('selected-image'))
                {
                    doneFlag = true;
                }

                if(innerinnerinnerdiv.length > 0)
                {
                    for(var l =0; l < innerinnerinnerdiv.length; l++)
                    {
                        if(car[l].className != undefined && innerinnerinnerdiv[l].className.includes('selected-image'))
                        {
                            doneFlag = true;
                        }

                        var innermostdiv = innerinnerinnerdiv[l].childNodes
                        if(innermostdiv.length>0)
                        {
                            for(var m = 0; m < innermostdiv.length; m++)
                            {
                                if( innermostdiv[m].className != undefined && innermostdiv[m].className.includes('selected-image'))
                                {
                                    doneFlag = true
                                }
                            }
                        }
                    }
                }
            }
        }

        if (doneFlag)
        {
            document.getElementById('getsearchbutton').innerHTML = 'Done';
            animateById('getsearchbutton','shake');
            $('#getsearchbutton').css('background','#43A047');
            $('#getsearchbutton').css('color','white');
        }
            else
        {
            document.getElementById('getsearchbutton').innerHTML = 'Reload Images';
            animateById('getsearchbutton','shake');
            $('#getsearchbutton').css('background','#424242');
        }
    }
}

function sendEmail()
{
    for(var i = 0; i < imagesselected.length; i++)
    {
        try
        {
            selectedimages+= "  "+ imagemap.get(imagesselected[i]);
        }catch(e)
        {
            
        }
    }

    var message =   "Client Name  = "+ document.getElementById('name').value +";"+ 
                    "Client Email = "+ document.getElementById('email').value +";"+ 
                    "Product description = "+document.getElementById('info-textarea').value +";"+
                    "Selected images = " + selectedimages;
    getHttpRequest("POST","/the_lance_application/email",message);
}


function submitfile()
{
    var file = document.getElementById('fileinput').files[0];
    getHttpRequest("POST","/the_lance_application/computervision",file,'cv')
}

function getSearch(result) 
{   
    deleteOldImages();
    appendImages();
    
    JSON.parse(result,function(key,value){
        if(key == "thumbnailUrl"){
            array.push(value);
        }
    });

    
    for(count =0; count <=6; count++)
    {
        if(count<= 2)
        {
            var divcontent = "<div class='col-lg-4' id='AppendedImages'><img id='AppendImage' src=\""+generateURL()+"\" onclick=\"getUrl(this)\"/></div>";
            document.getElementById('topAppendedImages').innerHTML = document.getElementById('topAppendedImages').innerHTML+ divcontent;
            animateById('AppendImage','zoomIn');
        }

        if(count> 2 && count <= 5)
        {
            var divcontent = "<div class='col-lg-4' id='AppendedImages'><img id='AppendImage' src=\""+ generateURL() +"\" onclick=\"getUrl(this)\"/></div>";
            document.getElementById('bottomAppendedImages').innerHTML = document.getElementById('bottomAppendedImages').innerHTML +divcontent;
            animateById('AppendImage','zoomIn');
        }

        if(count === 6){
            var divcontent = "<div id='AppendedImage'><img id='AppendImage' src=\""+ generateURL() +"\" onclick=\"getUrl(this)\"/></div>";
            document.getElementById('sideAppendedImages').innerHTML = divcontent;
            animateById('AppendImage','zoomIn');
        }
    }

    var carouselcontent = "<div class=\"carouselbox\"><ol class=\"content\"><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li><li><img id=\"AppendImage\" src=\""+generateURL()+"\" onclick=\"getUrl(this)\" /></li></ol><div class=\"buttons\"><button class=\"prev\">◀</button><button class=\"next\"> ▶ </button></div></div>";
    setTimeout(function(){carousel()},20);
    document.getElementById("carousel_div").innerHTML = carouselcontent; 
    animateById('AppendImage','zoomIn');
    document.getElementById("getsearchbutton").innerHTML = "Reload Images";
}

