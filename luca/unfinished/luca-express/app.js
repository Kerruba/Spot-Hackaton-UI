var express = require('express')
var marked = require('marked')
var app = express()

var index = require('./routes/index');
var helps = require('./routes/help');

app.set('view engine', 'pug')

app.use('/', index)
app.use('/help', helps)


// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

app.listen(8080, function() {
  console.log("Listening on port 8080")
})