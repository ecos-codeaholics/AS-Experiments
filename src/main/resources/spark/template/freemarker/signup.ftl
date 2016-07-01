<#import "layout.ftl" as layout />

<@layout.masterTemplate>

<h1>${title}</h1>

<p>Already a user? <a href="/login">Login</a></p>

<form class="pure-form pure-form-stacked" method="post">
    <fieldset>
        <legend>
            Please provide your information to register to the app
        </legend>
        <label for="name">Name</label>
        <input id="name" type="text" placeholder="First name" name="name">
        <label for="last-name">Last name</label>
        <input id="last-name" type="text" placeholder="Last name" name="last-name">
        <label for="email">Email</label>
        <input id="email" type="email" placeholder="Email" name="email">
        <label for="password">Password</label>
        <input id="password" type="password" placeholder="Password" name="password">
        <label for="remember" class="pure-checkbox">
            <input id="remember" type="checkbox"> I've read the terms and conditions
        </label>
        <button type="submit" class="pure-button pure-button-primary">Submit</button>
    </fieldset>
</form>

</@layout.masterTemplate>
