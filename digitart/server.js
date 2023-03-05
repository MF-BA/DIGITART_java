const express = require('express');
const multer = require('multer');

const app = express();

// configure multer middleware to handle file uploads
const upload = multer({

  dest: 'public/images' // directory where uploaded files will be stored

});

// define route handler for file uploads
app.post('/images', upload.single('imageData'), async (req, res) => {
  try {
  // The uploaded image file is saved to req.file.path
  const imagePath = `http://localhost:80/images/${req.file.filename}`;

    res.status(200).send('Image uploaded successfully');

  // handle uploaded file here
  //res.set('Access-Control-Allow-Origin', '*');
  //res.send('File uploaded successfully');
   }catch (error) {
    console.error(error);
    res.status(500).send('Internal server error');
    
   }
});

// start server on port 80
app.listen(80, () => {
  console.log('Server started on port 80');
});

/*const fs = require('fs');

fs.readFile('D:/xamp/htdocs/images/imageName.jpg', function (err, data) {
  if (err) throw err;
  // process the image data here
});*/

