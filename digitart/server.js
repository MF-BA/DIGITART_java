const express = require('express');
const multer = require('multer');

const app = express();

// configure multer middleware to handle file uploads
const upload = multer({
  dest: 'D:/xamp/htdocs/images' // directory where uploaded files will be stored
});

// define route handler for file uploads
app.post('/upload', upload.single('file'), (req, res) => {
  // handle uploaded file here
  res.send('File uploaded successfully');
});

// start server on port 8080
app.listen(8080, () => {
  console.log('Server started on port 8080');
});

/*const fs = require('fs');

fs.readFile('D:/xamp/htdocs/images/imageName.jpg', function (err, data) {
  if (err) throw err;
  // process the image data here
});*/

