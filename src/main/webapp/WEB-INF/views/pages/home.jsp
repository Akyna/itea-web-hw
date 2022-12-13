<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/component/header.jsp" %>

<h2>Ласкаво просимо до
    магазину ${sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? sessionScope[ApplicationConstant.APP_USER].fullName : "госте"}!</h2>
<p> Наші професійні менеджери допоможуть швидко та якісно підібрати потрібний Вам товар. Телефонуйте цілодобово!
    Втюхаємо все!</p>
<p>На жаль, Ви можете натрапити на схожий дизайн в інтернеті - це все тому, що автор ледар і двієчник і не знає, як
    змінити шаблон.</p>
<p>Сподіваємося, що це не позначиться на вашій купівельній спроможності.</p>


<%@ include file="/WEB-INF/views/component/footer.jsp" %>