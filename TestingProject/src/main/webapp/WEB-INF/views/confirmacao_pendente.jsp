<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Portal do Utente</title>

   <!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
	
    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body background=<c:url value="/resources/gfx/Final2.png"/> />

	<div class= "container">   
	        
	            <div class="col-lg-12"> 
	                <div class= "rowMajor">

					<div class="col-md-6"><a id = "PortalDoUtente" href="/" style="text-decoration:none; color: white; font-weight:100">Portal do Utente</a></div>
					<div class="col-md-6" id="entrar" style="text-align: right;">${username}</div>
					</div>
	            
	            </div>
				
		<div class="col-lg-12" id="caixaGig">  <!--  -->
		
			<div id="wrapper">
				
			
			
					<!-- Page Content -->
					<div id="page-content-wrapper">
						<div class="container-fluid">
							
								<form method="post" class="col-lg-12">
									<h1>Confirmação do Registo</h1>
									<p class="newP">Receberá um e-mail do Portal do Utente com o código de autenticação.</p>
									<p>  Insira o código: <input id="codigo1" type="text" name="codigo"> </p>
									<div id="erro"></div>
									
								</form>
								<button id="botao_conf1" type="submit" onclick="lolada()"> Submeter</button>
						
						</div>
					</div>
					<!-- /#page-content-wrapper -->
	
					
		
	    </div>
	    <!-- /#wrapper -->
	
	    <!-- jQuery -->
	   <!-- <script src="js/jquery.js">  </script> -->
	    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <!-- <script src="js/bootstrap.min.js"></script> -->
	    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
	    
	    <!-- Menu Toggle Script -->
	    <script>
	    $("#menu-toggle").click(function(e) {
	        e.preventDefault();
	        $("#wrapper").toggleClass("toggled");
	    });
	    
	    path="https://" + window.location.host + "/";
		warning=null;
		function lolada(){
			$.post(path + 'verifyCode', $("form").serialize()).done(function( data ) { 
				if (data=="true"){window.location.replace(path + "index");}
				else if(warning==null){ $("#erro").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 3%;">Código Incorrecto</div>');
				warning=true;}
				
				});
			}
	    </script>
	</div>
</body>



</html>
