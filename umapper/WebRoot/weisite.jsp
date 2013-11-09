<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>Free HTML5 Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">

	<!-- The styles -->
	<link id="bs-css" href="charisma/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="charisma/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="charisma/css/charisma-app.css" rel="stylesheet">
	<link href="charisma/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='charisma/css/fullcalendar.css' rel='stylesheet'>
	<link href='charisma/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='charisma/css/chosen.css' rel='stylesheet'>
	<link href='charisma/css/uniform.default.css' rel='stylesheet'>
	<link href='charisma/css/colorbox.css' rel='stylesheet'>
	<link href='charisma/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='charisma/css/jquery.noty.css' rel='stylesheet'>
	<link href='charisma/css/noty_theme_default.css' rel='stylesheet'>
	<link href='charisma/css/elfinder.min.css' rel='stylesheet'>
	<link href='charisma/css/elfinder.theme.css' rel='stylesheet'>
	<link href='charisma/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='charisma/css/opa-icons.css' rel='stylesheet'>
	<link href='charisma/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="charisma/img/favicon.ico">
		
</head>

<body>
		<!-- topbar starts -->
		<jsp:include page="header.jsp"></jsp:include>  
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<jsp:include page="menu.jsp"></jsp:include>  
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Dashboard</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid">
				<div class="span12">
					<div class="box span4" style="height:500px">
					模拟屏幕显示
					</div>
					
					<div class="box span7" style="height:500px">
					<form class="form-horizontal">
							<fieldset>
								<div class="control-group">
								<label class="control-label" for="selectError">type</label>
								<div class="controls">
								  <select id="selectError" data-rel="chosen">
									<option>十宫格</option>
									<option selected>单图文</option>
									<option>三宫格</option>
								  </select>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInputButton">name</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInputButton" size="16" type="text"><button class="btn" type="button">Go!</button>
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="appendedInputButtons">url</label>
								<div class="controls">
								  <div class="input-append">
									<input id="appendedInputButtons" size="16" type="text"><button class="btn" type="button">Search</button><button class="btn" type="button">Options</button>
								  </div>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label">图片上传</label>
								<div class="controls">
								  <input type="file">
								</div>
							  </div>
							  <div class="form-actions" style="margin-top:250px">
								<button type="submit" class="btn btn-primary">Save changes</button>
								<button class="btn">Cancel</button>
							  </div>
							</fieldset>
						</form>
					
					</div>
					</div>
			</div>
					

		<jsp:include page="footer.jsp"></jsp:include>  
		</div>
		</div>
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="charisma/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="charisma/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="charisma/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="charisma/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="charisma/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="charisma/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="charisma/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="charisma/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="charisma/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="charisma/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="charisma/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="charisma/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="charisma/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="charisma/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="charisma/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="charisma/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='charisma/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='charisma/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="charisma/js/excanvas.js"></script>
	<script src="charisma/js/jquery.flot.min.js"></script>
	<script src="charisma/js/jquery.flot.pie.min.js"></script>
	<script src="charisma/js/jquery.flot.stack.js"></script>
	<script src="charisma/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="charisma/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="charisma/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="charisma/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="charisma/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="charisma/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="charisma/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="charisma/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="charisma/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="charisma/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="charisma/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="charisma/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="charisma/js/charisma.js"></script>
	
</body>
</html>
