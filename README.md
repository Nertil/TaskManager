DESCRIPTION
============
This is a simple demo app to show communication between sencha touch and REST web services realized in Java.

AUTHOR
======    
[Nertil Qatipi](https://www.linkedin.com/in/nertilqatipi)

LICENCE
=======    
[GNU GPL v3.0](http://www.gnu.org/copyleft/gpl.html)    

TOOLS AND FRAMEWORKS
====================

Operating system: windows 8.1    
ST2 version: [v2.4.0](http://www.sencha.com/products/touch/)    
Sencha Cmd: [v5.0.3.324](http://www.sencha.com/products/sencha-cmd/)    
Sencha Architect: [v3.1.0](http://www.sencha.com/products/architect/)    
Netbeans: [v8.0.1](https://netbeans.org/)    
Cordova: [v3.6.3](http://cordova.apache.org/)    
MySQL: [v5.6.17](http://dev.mysql.com/downloads/windows/installer/)    
MySQL Workbench: [v6.2.3](http://www.mysql.it/products/workbench/)    

OTHER COMPONENTS
=================
[Component for the localization writed by Mitchell Simoens](https://github.com/mitchellsimoens/Ux.locale.Manager)    

LIVE DEMO
=========
http://178.239.178.23/task_manager/

[I recommend ripple plugin for chrome](https://chrome.google.com/webstore/detail/ripple-emulator-beta/geelfhphabnejjhdalkjhgipohgpdnoc)     

You can find a generate apk for android under the folder "mobile/generated apk".

TIPS
====

After you initialized cordova you have to copy manyaly the the files from "mobile/app/locales" into "mobile/cordova/www/app/locales" and also the resource dir because there is a [bug](http://www.sencha.com/forum/showthread.php?294230) in sencha cmd that dont copy the resources.
