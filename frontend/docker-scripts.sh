docker build -t 1239uth/emporium-shopping-frontend:1.0 .
docker run --name emporium-shopping-frontend -p 3000:8081 1239uth/emporium-shopping-frontend:1.0
#docker push 1239uth/emporium-shopping:latest