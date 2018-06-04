<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <div class="footer">
            <div class="footer-bottom">
                <div class="wrap">
                    <div class="copyrights col-md-6">
                        <p> © 2017 ShareIt. All Rights Reserved | Design by  <a href=""> Nguyễn Văn Hoàng</a></p>
                    </div>
                    <div class="footer-social-icons col-md-6">
                        <ul>
                            <li><a class="facebook" href=""></a></li>
                            <li><a class="twitter" href=""></a></li>
                            <li><a class="flickr" href=""></a></li>
                            <li><a class="googleplus" href=""></a></li>
                            <li><a class="dribbble" href=""></a></li>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!-- footer-section-ends-here -->
        <script type="text/javascript">
            $(document).ready(function() {
            		/*
            		var defaults = {
            		wrapID: 'toTop', // fading element id
            		wrapHoverID: 'toTopHover', // fading element hover id
            		scrollSpeed: 1200,
            		easingType: 'linear' 
            		};
            		*/
            $().UItoTop({ easingType: 'easeOutQuart' });
            });
        </script>
        <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
        <!---->
    </body>
</html>