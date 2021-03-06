#!/bin/bash

# Copyright 2018 Inscope Metrics
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

/usr/bin/systemctl stop telegraf
/usr/bin/systemctl stop metrics-portal
/usr/bin/systemctl stop cluster-aggregator
/usr/bin/systemctl stop mad
/usr/bin/systemctl stop grafana-server
/usr/bin/systemctl stop kairosdb
/usr/bin/systemctl stop scylla-server
