docker build -t 1239uth/emporium-shopping-backend:1.0 .
docker run --name emporium-shopping-backend -p 8080:8080 1239uth/emporium-shopping-backend:1.0
#docker push 1239uth/emporium-shopping:latest