<div class="main-container-wrapper">
    <div class="main-container">
        <form class="main-form" method="post" action="">
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Email</div>
                    <label>
                        <input
                                name="email"
                                pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                value="${requestScope.email}"
                                required
                        >
                    </label>
                </div>
                <div class="input-error">
                    ${requestScope.emailError}
                </div>
            </div>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Password</div>
                    <label>
                        <input
                                name="password"
                        <%--                                pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"--%>
                                value="${requestScope.password}"
                                required
                        >
                    </label>
                </div>
                <div class="input-error">
                    ${requestScope.passwordError}
                </div>
            </div>
            <div class="form-row">
                <div class="input-wrapper">
                    <input type="submit" value="Confirm" name="submit">
                </div>
                <div class="input-error"></div>
            </div>
        </form>
        <h4 style="color: red">${requestScope.errorMessage}</h4>
    </div>
</div>