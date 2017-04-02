var express = require('express')
var router = express.Router();
var marked = require('marked')
var Promise = require('bluebird')
var readFile = Promise.promisify(require("fs").readFile);
var path = require('path')


function getHelpPage(page) {
  let filepath = path.join(__dirname, `../views/help/${page}.md`)
  console.log(filepath)
  return readFile(filepath, 'utf8')
}


router.get('/:page', function(req, res) {
  let page = req.params.page
  getHelpPage(page)
    .then(result => {
      res.send(marked(result))
    })
    .catch(error => {
      console.log(error)
      res.sendFile(path.join(__dirname, '../views/error.html'))
    })
})

module.exports = router