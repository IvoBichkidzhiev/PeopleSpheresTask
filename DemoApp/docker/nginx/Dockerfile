# Use the official Nginx image
FROM nginx:latest

# Remove the default nginx.conf
RUN rm /etc/nginx/conf.d/default.conf

# Replace with our custom nginx.conf
COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80
EXPOSE 80
