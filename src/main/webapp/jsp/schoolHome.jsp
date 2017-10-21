<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation smoothscroll scrollTo">
  <head>
    <!-- Site Title-->
    <title>学院之家</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Stylesheets-->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400%7CQuicksand:400,700">
    <link rel="stylesheet" href="css/style.css">
		<!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
		<![endif]-->
  </head>
  <body>
    <!-- Page-->
    <div class="page text-center">
      <!-- Page Header-->
        <jsp:include page="header.jsp"/>
        <script type="text/javascript">
            var div = document.getElementById("index");
            div.setAttribute("class", "active");
            div = document.getElementById("headerbackground");
            div.parentNode.removeChild(div);
        </script>
      <!-- Swiper-->
      <div data-height="" data-min-height="300px" data-simulate-touch="false" data-slide-effect="fade" class="swiper-container swiper-slider">
        <div class="swiper-wrapper">
          <div data-slide-bg="images/backgrounds/bg-ecnu1.jpg" class="swiper-slide"></div>
          <div data-slide-bg="images/backgrounds/bg-ecnu2.jpg" class="swiper-slide"></div>
          <div data-slide-bg="images/backgrounds/bg-ecnu4.jpg" class="swiper-slide"></div>
        </div>
        <div class="swiper-caption-absolute">
          <div class="shell">


            <div class="range range-xs-center">
              <div class="cell-sm-10 cell-lg-8 offset-top-90">
                <div>
                  <div class="text-extra-big font-accent text-bold text-spacing-inverse-50 text-capitalize text-white ">
                    ECNU</div>
                </div>
                <div class="offset-top-15 offset-md-top-0">
                  <h5 class="text-spacing-75 text-white" style="font-family: 微软雅黑 ">一起在丽娃河畔写代码。</h5>
                </div>
                
              </div>


            </div>

          </div>
        </div>
        <div style="height:33px;" class="swiper-bottom-panel">
          <div class="swiper-bottom-panel-svg-wrap">
            <svg width="104" height="33">
              <path style="fill:#ffffff;fill-opacity:1" d="m 0.01291655,16.605668 0,-16.62382923 5.25000005,0.439007 C 12.962198,1.0646618 18.007648,4.7929098 22.364223,13.057557 c 6.80539,12.91018 16.468604,19.152403 29.648693,19.152403 13.180088,0 22.843308,-6.242223 29.648698,-19.152403 4.35657,-8.2646472 9.40202,-11.9928952 17.1013,-12.63671123 l 5.250006,-0.439007 0,16.62382923 0,16.62383 -52.000004,0 -51.99999945,0 0,-16.62383 z"></path>
            </svg><span data-custom-scroll-to="sec" class="icon material-icons-ico material-icons-expand_more text-white"></span>
          </div>
        </div>
      </div>
      <!-- Page Content-->
      <main class="page-content">
        <!-- Sailing Yachts-->
        <section id="sec" class="section-75 section-md-top-60 section-md-bottom-60">
          <div class="shell">
           
          </div>
        </section>


          <div class="range range-xs-center list-index-lg offset-md-top-0" style="margin-top: -40px">
              <div class="cell-sm-10 cell-md-4">

                  <div class="offset-top-20 offset-md-top-30"><img src="images/pages/blog.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                  <div class="offset-top-25 offset-md-top-40">
                      <ul class="list-marked p text-center">
                          <c:forEach items="${hottestBlog}" var="blog">
                              <li class="offset-top-15"><a href="singleBlog?blogID=${blog.blogId}" class="text-primary">
                                  <c:choose>
                                      <c:when test="${fn:length(blog.blogTitle)>20}">
                                          ${fn:substring(blog.blogTitle,0,18)}......
                                      </c:when>
                                      <c:otherwise>
                                          ${blog.blogTitle}
                                      </c:otherwise>
                                  </c:choose>
                              </a></li>
                          </c:forEach>
                      </ul>
                  </div>
                  <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="#" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                  <div class="offset-top-20">
                      <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                  </div>
              </div>
              <div class="cell-sm-10 cell-md-4 offset-top-64 offset-md-top-0">

                  <div class="offset-top-20 offset-md-top-30"><img src="images/pages/bbs.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                  <div class="offset-top-25 offset-md-top-40">
                      <div class="offset-top-25 offset-md-top-40">
                          <ul class="list-marked p text-center">
                              <c:forEach items="${hottestForumpost}" var="forumpost">
                                  <li class="offset-top-15"><a href="singleForumpost?forumpostID=${forumpost.forumpostId}" class="text-primary">
                                      <c:choose>
                                          <c:when test="${fn:length(forumpost.forumpostTitle)>20}">
                                              ${fn:substring(forumpost.forumpostTitle,0,18)}......
                                          </c:when>
                                          <c:otherwise>
                                              ${forumpost.forumpostTitle}
                                          </c:otherwise>
                                      </c:choose>
                                  </a></li>
                              </c:forEach>
                          </ul>
                      </div>
                  </div>
                  <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="#" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                  <div class="offset-top-20">
                      <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                  </div>
              </div>
              <div class="cell-sm-10 cell-md-4 offset-top-64 offset-md-top-0">

                  <div class="offset-top-20 offset-md-top-30"><img src="images/pages/zl.png" width="370" height="87" alt="" class="img-responsive center-block"></div>
                  <div class="offset-top-25 offset-md-top-40">
                      <div class="offset-top-25 offset-md-top-40">
                          <ul class="list-marked p text-center">
                              <c:forEach items="${hottestResource}" var="resource">
                                  <li class="offset-top-15"><a href="singleResource?resourceID=${resource.resourceId}" class="text-primary">
                                      <c:choose>
                                          <c:when test="${fn:length(resource.resourceName)>20}">
                                              ${fn:substring(resource.resourceName,0,18)}......
                                          </c:when>
                                          <c:otherwise>
                                              ${resource.resourceName}
                                          </c:otherwise>
                                      </c:choose>
                                  </a></li>
                              </c:forEach>
                          </ul>
                      </div>
                  </div>
                  <div class="btn-both-lines btn-both-lines-gray-lighter offset-top-15 offset-md-top-35"><a href="#" class="btn-link text-spacing-inverce-25 text-turquoise">更多</a></div>
                  <div class="offset-top-20">
                      <h5 class="text-spacing-20 text-silver-chalice">...</h5>
                  </div>
              </div>
          </div>

          <section class="section-75 section-md-top-90 section-md-bottom-103">
          <div class="shell">
            <div>
              <div class="range range-xs-center">
                              <div class="cell-sm-10">
                  <!-- Classic Responsive Table-->
                  <table class="table table-custom table-fixed text-bold text-left">
                    <tr class="font-accent bg-primary">
                      <th class="text-white">用户经验排行榜</th>
                      <th class="text-white">用户等级</th>
                      <th class="text-white">用户经验</th>
                      <th class="text-white">发帖数</th>
                      <th class="text-white">发表博客数</th>
                      <th class="text-white">发表资源数</th>
                    </tr>
                    <tr>
                      <td class="font-accent text-spacing-inverse-25 text-primary">Uquila</td>
                      <td>15</td>
                      <td>8000</td>
                      <td>51</td>
                      <td>7</td>
                      <td>6</td>
                    </tr>
                    <tr>
                      <td class="font-accent text-spacing-inverse-25 text-primary">Inglesea</td>
                      <td>15</td>
                      <td>7000</td>
                      <td>36</td>
                      <td>4</td>
                      <td>4</td>
                    </tr>
                    <tr>
                      <td class="font-accent text-spacing-inverse-25 text-primary">Emilia</td>
                      <td>15</td>
                      <td>6000</td>
                      <td>36</td>
                      <td>4</td>
                      <td>4</td>
                    </tr>
                    <tr>
                      <td class="font-accent text-spacing-inverse-25 text-primary">Trillium</td>
                      <td>14</td>
                      <td>5000</td>
                      <td>35</td>
                      <td>3</td>
                      <td>3</td>
                    </tr>
                    <tr>
                      <td class="font-accent text-spacing-inverse-25 text-primary">Hakeem</td>
                      <td>13</td>
                      <td>4600</td>
                      <td>35</td>
                      <td>3</td>
                      <td>2</td>
                    </tr>
                  </table>
                </div>
                <div class="cell-sm-10">
                  <!-- Classic Responsive Table-->
                  <table class="table table-custom table-fixed text-bold text-left">
                    <tr class="font-accent bg-primary">
                      <th class="text-white">本周活跃排行榜</th>
                      <th class="text-white">用户等级</th>
                      <th class="text-white">用户经验</th>
                      <th class="text-white">一周发帖数</th>
                      <th class="text-white">一周发表博客数</th>
                      <th class="text-white">一周发表资源数</th>
                    </tr>
                      <tr>
                          <td class="font-accent text-spacing-inverse-25 text-primary">Uquila</td>
                          <td>15</td>
                          <td>8000</td>
                          <td>51</td>
                          <td>7</td>
                          <td>6</td>
                      </tr>
                      <tr>
                          <td class="font-accent text-spacing-inverse-25 text-primary">Inglesea</td>
                          <td>15</td>
                          <td>7000</td>
                          <td>36</td>
                          <td>4</td>
                          <td>4</td>
                      </tr>
                      <tr>
                          <td class="font-accent text-spacing-inverse-25 text-primary">Emilia</td>
                          <td>15</td>
                          <td>6000</td>
                          <td>36</td>
                          <td>4</td>
                          <td>4</td>
                      </tr>
                      <tr>
                          <td class="font-accent text-spacing-inverse-25 text-primary">Trillium</td>
                          <td>14</td>
                          <td>5000</td>
                          <td>35</td>
                          <td>3</td>
                          <td>3</td>
                      </tr>
                      <tr>
                          <td class="font-accent text-spacing-inverse-25 text-primary">Hakeem</td>
                          <td>13</td>
                          <td>4600</td>
                          <td>35</td>
                          <td>3</td>
                          <td>2</td>
                      </tr>
                  </table>
                </div>
              </div>
              <div class="offset-top-47"><svg width="135" height="4" viewBox="0 0 135 4"><path style="fill:#efefef" d="M 2.3227821,2.0449621 -0.03800192,0.07659815 3.1569295,1.2434136 c 2.5401262,0.9276753 3.7227371,0.8843425 5.7702062,-0.2114297 1.9784883,-1.05885455 2.8486773,-1.10484315 3.7550683,-0.19845145 1.484362,1.48436175 5.031902,1.52768545 6.480446,0.079141 0.80087,-0.80087 2.091954,-0.7421935 4.740217,0.21543085 2.979173,1.0772827 4.014876,1.0816979 5.707987,0.024332 1.636053,-1.02173165 2.672814,-1.04140395 4.959784,-0.09411 2.271014,0.9406851 3.546951,0.926086 5.946995,-0.068045 2.294075,-0.95023691 3.340846,-0.9804738 4.2,-0.1213203 1.486272,1.48627181 5.021657,1.46707331 6.524159,-0.035429 0.906391,-0.9063917 1.77658,-0.8604031 3.755068,0.19845151 1.998288,1.0694512 3.235083,1.1291955 5.520207,0.2666579 2.160373,-0.81544991 3.729489,-0.81544991 5.889862,0 2.285124,0.8625376 3.521919,0.8027933 5.520207,-0.2666579 1.978488,-1.05885461 2.848677,-1.10484321 3.755068,-0.19845151 1.484362,1.48436181 5.031902,1.52768551 6.480446,0.079141 0.80087,-0.80087 2.071309,-0.7496588 4.664419,0.18802211 2.948695,1.0662618 4.004871,1.0526028 6.119554,-0.079141 1.963517,-1.05084213 2.82919,-1.09441383 3.735581,-0.18802223 1.484362,1.48436183 5.031906,1.52768553 6.480446,0.079141 0.80087,-0.80087 2.07131,-0.7496587 4.66442,0.18802203 2.90135,1.0491408 4.00935,1.0502085 5.96095,0.00574 1.95708,-1.04739773 2.78238,-1.04236583 4.49536,0.027409 1.72387,1.0765766 2.7384,1.078836 5.68684,0.012665 2.7988,-1.01205733 3.90688,-1.03357323 5.03447,-0.097755 0.7952,0.6599557 2.61547,0.976326 4.04505,0.703045 2.37877,-0.4547301 2.464,-0.3616463 1.00491,1.097441 -1.45908,1.4590874 -1.84642,1.4453923 -4.56661,-0.1614625 -2.74627,-1.6222625 -3.15865,-1.6336716 -5.42297,-0.1500332 -2.18024,1.4285487 -2.76388,1.4437811 -5.28891,0.1380357 -2.47017,-1.2773717 -3.19253,-1.2820786 -5.57019,-0.036295 -2.37022,1.241885 -3.09846,1.241885 -5.5,0 -2.40452,-1.2434263 -3.13028,-1.2416208 -5.52624,0.013748 C 99.763844,3.9190467 99.15734,3.9133435 97.292676,2.607281 95.404183,1.2845297 94.833305,1.2870756 92.236706,2.6298291 89.597447,3.9946429 89.064352,3.982798 86.815628,2.5093781 84.52735,1.0100401 84.120628,1.0069808 81.884292,2.4722843 79.735334,3.8803355 79.122431,3.8992836 76.704532,2.6324181 74.291896,1.3683104 73.641159,1.3851356 71.292806,2.7723415 68.845881,4.2177761 68.408458,4.2082649 66.042738,2.6581862 63.671614,1.1045661 63.264662,1.0965199 61.032448,2.5591227 58.880523,3.969118 58.285419,3.9846813 55.824584,2.69532 53.371101,1.4098101 52.768844,1.4213922 50.673394,2.7943834 48.512048,4.2105516 48.042674,4.1968405 45.408131,2.6405778 42.740652,1.0648591 42.325185,1.0568632 40.065069,2.5377482 37.884828,3.9662969 37.30119,3.9815293 34.776157,2.6757839 32.280231,1.3850906 31.599207,1.3855448 29.12951,2.6795501 26.631592,3.9883412 26.052244,3.9748646 23.889307,2.5576544 21.678633,1.1091646 21.182621,1.1064374 18.434276,2.5276615 15.749926,3.9157928 15.172991,3.9243054 13.287423,2.6036024 11.426177,1.2999346 10.817722,1.295134 8.3625636,2.5647457 4.8669338,4.3724061 5.142697,4.3961383 2.3227821,2.0449621 Z"/></svg></div>
            </div>
          </div>
        </section>
      </main>
      <!-- Page Footer-->
	<jsp:include page="footer.jsp"/>
	</div>
	<!-- Global Mailform Output-->
	<div id="form-output-global" class="snackbars"></div>
	<!-- PhotoSwipe Gallery-->
	<div tabindex="-1" role="dialog" aria-hidden="true" class="pswp">
		<div class="pswp__bg"></div>
		<div class="pswp__scroll-wrap">
			<div class="pswp__container">
				<div class="pswp__item"></div>
				<div class="pswp__item"></div>
				<div class="pswp__item"></div>
			</div>
			<div class="pswp__ui pswp__ui--hidden">
				<div class="pswp__top-bar">
					<div class="pswp__counter"></div>
					<button title="Close (Esc)"
						class="pswp__button pswp__button--close"></button>
					<button title="Share" class="pswp__button pswp__button--share"></button>
					<button title="Toggle fullscreen"
						class="pswp__button pswp__button--fs"></button>
					<button title="Zoom in/out" class="pswp__button pswp__button--zoom"></button>
					<div class="pswp__preloader">
						<div class="pswp__preloader__icn">
							<div class="pswp__preloader__cut">
								<div class="pswp__preloader__donut"></div>
							</div>
						</div>
					</div>
				</div>
				<div
					class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
					<div class="pswp__share-tooltip"></div>
				</div>
				<button title="Previous (arrow left)"
					class="pswp__button pswp__button--arrow--left"></button>
				<button title="Next (arrow right)"
					class="pswp__button pswp__button--arrow--right"></button>
				<div class="pswp__caption">
					<div class="pswp__caption__cent"></div>
				</div>
			</div>
		</div>
    </div>
    <!-- Java script-->
    <script src="js/core.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>