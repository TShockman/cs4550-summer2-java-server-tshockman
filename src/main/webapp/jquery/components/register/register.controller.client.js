(function () {
  const userClient = new UserServiceClient();
  const $registerBtn = $('#registerBtn');
  const $username = $('#username');
  const $password = $('#password');
  const $passwordConfirm = $('#passwordConfirm');

  $registerBtn.click(registerHandler);

  function registerHandler() {
    const username = $username.val();
    const password = $password.val();
    const passwordConfirm = $passwordConfirm.val();

    if (!password && password !== passwordConfirm) {
      return registrationFailed();
    }

    const userObj = new User(username, password);

    userClient.register(userObj)
      .then(registrationSucceeded, registrationFailed);
  }

  function registrationSucceeded() {
    window.location.href = '/jquery/components/profile/profile.template.client.html';
  }

  function registrationFailed() {
    alert('Registration failed');
  }
})();