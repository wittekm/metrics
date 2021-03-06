#!/bin/bash

# Copyright 2014 Groupon.com
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

uri=
name=
pid=

function show_help {
  echo "Usage:"
  echo "./pinger.sh -h|-?"
  echo "./pinger.sh -u URI -n NAME [-v]"
  echo ""
  echo " -h|-? -- print this help message"
  echo " -u uri -- specify a uri to ping"
  echo " -n name -- specify the name of the service"
  echo " -p pid -- specify the process id of the service"
  echo " -v -- verbose mode"
}

# Parse Options
OPTIND=1
while getopts ":h?vu:n:p:" opt; do
  case "$opt" in
    h|\?)
      show_help
      exit 0
      ;;
    v)
      verbose=1
      ;;
    n)
      name="$OPTARG"
      ;;
    p)
      pid="$OPTARG"
      ;;
    u)
      uri="$OPTARG"
      ;;
  esac
done

# Ping
while :
do
  result=
  if type curl >/dev/null 2>&1; then
    result=`curl -I -s -o /dev/null -w "%{http_code}" ${uri}`
  elif type wget >/dev/null 2>&1; then
    result=`wget -q -S --spider ${uri} 2>&1 | grep "HTTP/" | awk '{print $2}'`
  else
    echo "ERROR: No supported http query command found!"
    exit 1
  fi
  if [ "${result}" == "200" ]; then
    result="HEALTHY"
  else
    result="FAILING"
  fi
  if [ -n "$verbose" ]; then
    echo "Pinging ${uri} ${result} (${name}:${pid:-?})"
  else
    if [ "$result" == "FAILING" ]; then
      echo "${name}:${pid:-?} failing!"
    fi
  fi
  sleep 0.5
done
