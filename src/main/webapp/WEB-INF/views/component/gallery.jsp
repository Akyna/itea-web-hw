<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="poptrox">
    <!-- start -->
    <ul id="gallery">
        <li>
            <a href="${pageContext.request.contextPath}/static/images/1_big.jpg">
                <img src="${pageContext.request.contextPath}/static/images/1.jpg"
                     title="Phasellus nec erat sit amet nibh pellentesque congue."
                     alt=""/>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/static/images/2_big.jpg">
                <img src="${pageContext.request.contextPath}/static/images/2.jpg"
                     title="Phasellus nec erat sit amet nibh pellentesque congue."
                     alt=""/>
            </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/static/images/3_big.jpg">
            <img src="${pageContext.request.contextPath}/static/images/3.jpg"
                 title="Phasellus nec erat sit amet nibh pellentesque congue."
                 alt=""/>
        </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/static/images/4_big.jpg">
            <img src="${pageContext.request.contextPath}/static/images/4.jpg"
                 title="Phasellus nec erat sit amet nibh pellentesque congue."
                 alt=""/>
        </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/static/images/5_big.jpg">
            <img src="${pageContext.request.contextPath}/static/images/5.jpg"
                 title="Phasellus nec erat sit amet nibh pellentesque congue."
                 alt=""/>
        </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/static/images/6_big.jpg">
                <img src="${pageContext.request.contextPath}/static/images/6.jpg"
                     title="Phasellus nec erat sit amet nibh pellentesque congue."
                     alt=""/>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/static/images/7_big.jpg">
                <img src="${pageContext.request.contextPath}/static/images/7.jpg"
                     title="Phasellus nec erat sit amet nibh pellentesque congue."
                     alt=""/>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/static/images/8_big.jpg">
                <img src="${pageContext.request.contextPath}/static/images/8.jpg"
                     title="Phasellus nec erat sit amet nibh pellentesque congue."
                     alt=""/>
            </a>
        </li>
    </ul>
    <br class="clear"/>
    <script type="text/javascript">
        $('#gallery').poptrox();
    </script>
    <!-- end -->
</div>