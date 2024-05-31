Notepad++ v8.6.7 bug-fixes & new enhancements:

1. Fix regression of multi-edit cursors placed wrongly issue.
2. Fix multi-editing not showing multiple cursors in dark mode.
3. Add auto-completion for Go & Raku, function list for Raku.
4. Fix symbol '&' not showing in Document Switcher.
5. Allow syntax highlighting for custom tags in HTML.
6. Fix dialogs out of screen problem.


Notepad++ v8.6.6 bug-fixes & new enhancements:

 1. Update to scintilla 5.5.0 & Lexilla 5.3.2.
 2. Fix crash when crossing the 2GB file size threshold.
 3. Fix a performance issue due to URL recognition.
 4. Update to nlohman json 3.11.3.
 5. Fix multi-edit resists escape after typing issue.
 6. Make F3 & Shift-F3 work in Find Replace dialog.
 7. Allow Ctrl-TAB to switch tabs in FindReplace, PluginAdmin and UDL dialogs.
 8. Add programming language support for Go & Raku(Perl 6).
 9. Fix user defined auto-insert not working issue.
10. Enhance GUI: resize checkboxes/radio buttons as text length needs.
11. Enhance GUI: make sizing arrows more coherent in Find dialog.
12. Fix URL enclosed in apostrophes or backtick not working issue.
13. Fix wrong dropped file view issue.
14. Fix Korean(한)/English(영) key not working regression.
15. Fix the tab labels of some dialogs being cut in Dark mode.
16. Fix close button disappeared issue in Find Replace dialog.
17. Apply dark theme to checkbox buttons on Windows 11.
18. Fix menu bar cluttered in Dark Mode issue.
19. Fix Debug Info minor display regression.
20. Enhance Lua language syntax highlighting.
21. Enhance the function list support for Ada.


Get more info on
https://notepad-plus-plus.org/downloads/v8.6.7/


Included plugins:

1.  NppExport v0.4
2.  Converter v4.6
3.  Mime Tool v3.1


Updater (Installer only):

* WinGUp (for Notepad++) v5.2.8
public void alquilarLibro( Libro libro,Cliente cliente) {

       cliente=buscarCliente(cliente.getIdCliente());
       if((cliente!=null)&&(libro.getCopias()>0))
       {
           if (cliente.getBibliotecaPersonal().isEmpty()) {
               cliente.getBibliotecaPersonal().add(libro);
               libro.restarCopias();
           } else {
               cliente.getBibliotecaPersonal().add(libro);
               libro.restarCopias();
           }
       }

    }
	
	public void alquilarLibro( Libro libro,Cliente cliente) {

       cliente=buscarCliente(cliente.getIdCliente());
       if((cliente == null){
		   //No existe el cliente
	   }
	   
	   if(libro == null){
		   //No existe libro
	   }
	   
	   if ( cliente.getSaldo < libro.precio) {
		   //Saldo insuficiente
	   }
	   
	   if( libro.copias < 1){
	   //No disponible por falta de copias
	   }
	   //Si llego hasta aca alquilo
	   
	   if (cliente.getBibliotecaPersonal().isEmpty()) {
		   //No tenia alquileres encima
               cliente.getBibliotecaPersonal().add(libro);
               libro.restarCopias();
			   cliente.saldo -= libro.precio;
           } else {
			   //Tenia alquiler encima
               if(cliente.getBibliotecaPersonal.size() > 2){
				   //El cliente dispone de la maxima cantidad de alquileres permitidos
			   }else{
				  cliente.getBibliotecaPersonal().add(libro);
                  libro.restarCopias();
				  cliente.saldo -= libro.precio;
			   }
           }
	}
	