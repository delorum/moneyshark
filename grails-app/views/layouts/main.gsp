<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><g:layoutTitle default="MoneyShark" /></title>        
        <g:layoutHead />
        <g:javascript library="application" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" type="text/css" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <r:layoutResources />
    </head>
    <body>
        <%--<div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        --%>
        <g:render template="/layouts/header" />
        <g:render template="/layouts/globalNav" />
        <g:layoutBody />
        <g:render template="/layouts/footer" />
        <r:layoutResources />
    </body>
</html>