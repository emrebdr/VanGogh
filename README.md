# VanGogh (Image Editing Tool)

---

## Description

VanGogh is a simple image editing tool that allows you to edit images in a variety of ways. It is written in Java and uses the Java Image I/O API to read and write images.

## Features

* Open and save images in a variety of formats
* Edit images in a variety of ways
* - Resize images to a specific size or to a percentage of the original size (e.g. 50%)
* - Crop images to a specific size or to a percentage of the original size (e.g. 50%)
* - Merge all layers into one image
* - Write text on an image (e.g. "Hello World!") in a variety of fonts and colors (e.g. red) and sizes (e.g. 24) at a specific location (e.g. 100, 100) on the image

## Usage

Clone the repository and run the following command in the root directory of the project:

    $ docker build -t <image_name> .

Then, run the following command to run the program:

    $ docker run -d -p <internal_port>:<exposed_port> --env-file <env_file_path> <image_name>

## Environment Variables

The following environment variables are required to run the program:

* `VAN_GOGH_PORT` - The port that the program will run on
* `VAN_GOGH_HOST` - The host that the program will run on

## Request Examples

### Resize Image

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image"], "resize_width": 100, "resize_height": 100, "output": "path/to/output"}' \
        http://<host>:<port>/resize

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image"], "resize_ratio": 50, "output": "path/to/output"}' \
        http://<host>:<port>/resize

### Crop Image

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image"], "x": 100, "y": 100, "width": 200, "height": 200, "output": "path/to/output"}' \
        http://<host>:<port>/crop

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image"], "ratio": 50, "output": "path/to/output"}' \
        http://<host>:<port>/crop

### Merge Images

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image1", "path/to/image2"], "output": "path/to/output"}' \
        http://<host>:<port>/merge

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image1", "path/to/image2"], "positions": [[0, 0], [100, 100]], "output": "path/to/output"}' \
        http://<host>:<port>/merge

### Write Text on Image

    $ curl -X POST \
        -H "Content-Type: application/json" \
        -d '{"images": ["path/to/image"], "text": "Hello World!", "color": "red", "fontSize": 24, "x": 100, "y": 100, "output": "path/to/output"}' \
        http://<host>:<port>/write_text

Note: Output is optional. If not specified, the output will be saved to the same directory as the input image. 

## Response Examples


### Success

    {
        "message": "Operation completed successfully.",
        "status": "success"
    }

### Failure

    {
        "message": "Operation failed.",
        "status": "failed"
    }