#!/bin/bash
docker build -t fancy007/metadata-extractor .
docker run --mount type=bind,source=$PWD/pdftest/Files,target=/app/pdftest/Files fancy007/metadata-extractor
docker push fancy007/metadata-extractor