(function () {
  let currentUser = null;
  const userService = new UserServiceClient();

  const $username = $('#uusername');
  const $phone = $('#phone');
  const $email = $('#email');
  const $role = $('#role');
  const $dob = $('#dob');
  const $updateButton = $('#updateButton');
  const $logout = $('#logout');

  $updateButton.click(updateHandler);
  $logoutButton.click(logout);

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
    $username.val(user.getUsername());
    $phone.val(user.getPhone());
    $email.val(user.getEmail());
    $role.val(user.getRole());
    $dob.val(user.getDateOfBirth());
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