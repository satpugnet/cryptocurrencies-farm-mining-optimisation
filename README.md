# cryptocurrencies-farm-mining-optimisation

## Linux/Mac
curl -v --request POST 'https://crypto-mining-optimisation.herokuapp.com/' --data '{"userEmail":"saturnin.13@hotmail.fr", workerName='worker1', "data":{"sysconfig":{"OS":"linux"}, "benchMarking":[]}}'
curl -v --request POST 'http://localhost:8000/' --data '{"userEmail":"saturnin.13@hotmail.fr", workerName='worker1', "data":{"sysconfig":{"OS":"linux"}, "benchMarking":[]}}'

## Windows
Invoke-WebRequest 'https://crypto-mining-optimisation.herokuapp.com/' -Method 'POST' -Body '{"userEmail":"saturnin.13@hotmail.fr", "workerName": "pcsaturnin", "data":{"systemConfig":{"os":{"osType":"windows","dataModel":"x64","version":"6.3","environment":"POWERSHELL"},"gpus":{"gpus":[{"gpuType":"OPEN_CL","memorySize":2214592512},{"gpuType":"CUDA","memorySize":2147483648}]}}, "benchMarking":[]}}'
Invoke-WebRequest 'http://localhost:8000/' -Method 'POST' -Body '{"userEmail":"saturnin.13@hotmail.fr", "workerName": "pcsaturnin", "data":{"systemConfig":{"os":{"osType":"windows","dataModel":"x64","version":"6.3","environment":"POWERSHELL"},"gpus":{"gpus":[{"gpuType":"OPEN_CL","memorySize":2214592512},{"gpuType":"CUDA","memorySize":2147483648}]}}, "benchMarking":[]}}'

Invoke-WebRequest 'https://crypto-mining-optimisation.herokuapp.com/' -Method 'PUT' -Body "{userEmail:'saturnin.13@hotmail.fr',workerName:'workerPCsaturnin', currency:'BTC',hashrate:25.3}"
Invoke-WebRequest 'http://localhost:8000/' -Method 'PUT' -Body "{userEmail:'saturnin.13@hotmail.fr',workerName:'workerPCsaturnin', currency:'BTC',hashrate:25.3}"
