var express = require('express')
var marked = require('marked')
var app = express()

var index = require('./routes/index');
var helps = require('./routes/help');

app.set('view engine', 'pug')

app.use('/', index)
app.use('/help', helps)

app.listen(8080, function() {
  console.log("Listening on port 8080")
})