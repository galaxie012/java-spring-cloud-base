#!/bin/bash
set -eu

# example:
# run gateway.jar
# ./run.sh gateway
#
# run gateway when health check pass
# ./run.sh -h http://127.0.0.1:8888/actuator/health gateway
#
# run gateway when health check pass, or timeout (45s)
# ./run.sh -th http://127.0.0.1:8888/actuator/health gateway


while getopts th: opt
do
    case "$opt" in
        t) TIMEOUT_FLAG=T ;;
        h) HEALTH_URL="$OPTARG" ;;
        *) echo "Unknown option: $opt" ;;
    esac
done

shift $(( $OPTIND-1 ))

TIMEOUT_FLAG=${TIMEOUT_FLAG:-F}
HEALTH_URL=${HEALTH_URL:-}
APP=$1


if [ -n "${HEALTH_URL}" ]; then
    echo "health-check mode enabled."
    TEST_COMMAND=$(cat <<-END
    echo "Testing ${HEALTH_URL}..."
    while [[ "\$(curl -s -o /dev/null -L -w %{http_code} ${HEALTH_URL})" != "200" ]]; do
        echo "Waiting for ${0}" && sleep 2;
    done

    echo "OK!"
END
                )

    if [ "${TIMEOUT_FLAG}" == "T" ]; then
        if hash timeout 2>/dev/null; then
            echo "timeout mode enabled."
            timeout -s TERM 45 bash -c "${TEST_COMMAND}"
        else
            echo "timeout switch opened, but can't find timeout command."
            bash -c "${TEST_COMMAND}"
        fi
    else
        bash -c "${TEST_COMMAND}"
    fi
fi

if [[ "$APP" == *.jar ]]
then
    echo "java: $APP..."
    java -Djava.security.egd=file:/dev/./urandom -jar $APP
else
    echo "exec: $APP..."
    $APP
fi
