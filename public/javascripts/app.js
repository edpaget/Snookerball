(function() {
  var deleter = function(ev) {
    ev.preventDefault();
    $.ajax({
      type: 'DELETE',
      url: ev.target.href
    }).success(function() {
      $("#player-" + ev.target.dataset.id).remove();
    });;
  };

  var formSend = function(ev) {
    var form = $(ev.target.parentNode),
      method = form.attr('method'),
      href = form.attr('action');
    ev.preventDefault();
    $.ajax({
      type: method,
      url: href,
      data: form.serialize()
    }).success(function(response) {
      window.location.replace(href);
    });
  };

  $('.delete').on('click', deleter)
  $('button[type="submit"]').on('click', formSend)
}).call(this)
