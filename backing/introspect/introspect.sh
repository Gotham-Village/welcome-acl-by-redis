#!/usr/bin/env zsh
# This script attempts to extract the Redis configuration file for inspection.
: IMPORTANT: Redis image should contain NO config file and run on defaults alone.

container_name="introspect-redis"

# Start a temporary Redis container.
docker run --name $container_name -d redis:latest

# Since the default Redis container might not include the redis.conf file
# or might place it in a different location, we'll first check for its existence.
if docker exec $container_name ls /usr/local/etc/redis/redis.conf; then
    # If the file exists, copy it from the container to the host.
    docker cp $container_name:/usr/local/etc/redis/redis.conf .
else
    echo "The redis.conf file does not exist in the expected location. Attempting to locate it elsewhere..."

    # Attempt to find the redis.conf file anywhere within the container.
    # This command lists the location of redis.conf files within the container.
    configuration_found=$(docker exec $container_name find / -name "redis.conf" 2>/dev/null)

    if [[ -n "$configuration_found" ]]; then
        echo "Found redis.conf at: $configuration_found"
        # Assuming the found configuration path is singular, copy the first found instance to the host.
        docker cp "$container_name:$configuration_found" ./redis.conf
    else
        echo "No redis.conf file could be found inside the container."
    fi
fi

# Stop and remove the temporary container.
docker stop $container_name
docker rm $container_name
