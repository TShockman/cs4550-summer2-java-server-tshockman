(function () {
  const userService = new UserServiceClient();
  const $username = $('#username');
  const $password = $('#password');
  const $loginBtn = $('#loginBtn');

  $loginBtn.click(login);

  function login() {
    const user = new User($username.val(), $password.val());
    userService.login(user)
      .then(redirectToProfile);
  }

  function redirectToProfile() {
    window.location.href = '/jquery/components/profile/profile.template.client.html';
  }
})();