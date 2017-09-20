#!/bin/bash

set -e

gradle clean distTar

scp build/distributions/u2flib-server-demo-0.18.0.tar webauthn-demo:uploads/

SSH() {
  ssh -t webauthn-demo "$@"
}

SSH sudo systemctl stop webauthn-demo.service
SSH sudo rm -rf /webauthn-demo/u2flib-server-demo-0.18.0/bin
SSH sudo rm -rf /webauthn-demo/u2flib-server-demo-0.18.0/lib
SSH sudo -u webauthn-demo tar xf /home/emil/uploads/u2flib-server-demo-0.18.0.tar -C /webauthn-demo/
SSH sudo systemctl start webauthn-demo.service
