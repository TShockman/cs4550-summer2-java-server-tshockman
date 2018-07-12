(function () {
  let currentUser = null;
  const userService = new UserServiceClient();

  const $username = $('#username');
  const $phone = $('#phone');
  const $email = $('#email');
  const $role = $('#role');
  const $dob = $('#dob');
  const $updateButton = $('#updateButton');
  const $logout = $('#logout');

  $updateButton.click(updateHandler);
  $logout.click(logout);

  getProfile().then(renderProfile);

  function updateHandler() {
    const user = new User(
      null,
      null,
      null,
      null,
      $role.val(),
      $phone.val(),
      $email.val(),
      $dob.val());

    userService.updateProfile(user)
      .then(getProfile)
      .then(renderProfile);
  }

  function renderProfile(user) {
    if (!user) return;

    currentUser = user;
    $username.val(user.username);
    $phone.val(user.phone);
    $email.val(user.email);
    $role.val(user.role);
    $dob.val(user.dateOfBirth);
  }

  function getProfile() {
    return userService.getProfile();
  }

  function logout() {
    return userService.logout().then(redirectLogin);
  }

  function redirectLogin() {
    window.location.href = '/jquery/components/login/login.template.client.html';
  }
})();