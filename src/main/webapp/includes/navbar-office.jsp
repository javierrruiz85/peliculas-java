<div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        
                            <div class="sb-sidenav-menu-heading">Web</div>
                            <a class="nav-link" href="inicio">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Inicio
                            </a>
                            <a class="nav-link" href="peliculas">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Tabla peliculas
                            </a>
                            
                            <div class="sb-sidenav-menu-heading">Peliculas</div>
                            <a class="nav-link" href="vistas/frontoffice/peliculas">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Aprobadas
                            </a>
                            <a class="nav-link" href="vistas/frontoffice/peliculas?validadas=0">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Pendientes
                            </a>
                            
                            <div class="sb-sidenav-menu-heading">Edicion</div>
                            <a class="nav-link" href="vistas/frontoffice/crear-pelicula">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Crear pelicula
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Crear distribuidoras
                            </a>
                            
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                    	Datos de conexion
                        <div class="small">Sesion iniciada como: ${usuario_login.nombre}</div>
                        <div class="small">Rol: ${usuario_login.rol.nombre}</div>
                        
                    </div>
                </nav>
            </div>
            
           
            
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                    