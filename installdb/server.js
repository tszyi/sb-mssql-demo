var express = require("express");
var app = express();

var server = app.listen(8080, function () {
  console.log("Listening %s...", server.address().port);
});