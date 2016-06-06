<#import "layout.ftl" as layout />

<@layout.masterTemplate>

<h1>${title}</h1>
<p>Not a user? <a href="/user/signup">Sign up</a></p>

<form class="pure-form pure-form-stacked">
    <fieldset>
        <legend>
            Please provide your email and password to access the app
        </legend>
        <label for="email">Email</label>
        <input id="email" type="email" placeholder="Email">
        <label for="password">Password</label>
        <input id="password" type="password" placeholder="Password">
        <label for="remember" class="pure-checkbox">
            <input id="remember" type="checkbox"> Remember me
        </label>
        <button type="submit" class="pure-button pure-button-primary">Login</button>
    </fieldset>
</form>

</@layout.masterTemplate>