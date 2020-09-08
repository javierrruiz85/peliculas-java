<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Panel de control</title>
        
        <!-- Todas las rutas relativas comienzan con el href indicado -->
        <base href="${pageContext.request.contextPath}/" />
        
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
        
        <!-- CSS -->
        <link href="css/estilos-office.css" rel="stylesheet" />
    </head>
    
    
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="vistas/frontoffice/inicio">Mi panel</a>
           
            <!-- Navbar Logout-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <a class="nav-link  btn btn-outline-light" href="logout">Cerrar Sesion</a>
            </form> 
            
        </nav>
        
        
                    