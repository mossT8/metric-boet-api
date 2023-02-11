// Utility Methods
const axios = require('axios');
const endpoint = 'http://localhost:8080/metrics/add';

async function insertMetric(device, name, value, username, password, ip) {
    try {
        const secondFactor = 1; // 1 = one second
        await new Promise(resolve => setTimeout(resolve, 1000 * secondFactor)); // simulate device waiting
        const response = await axios.post(endpoint, {
            device,
            name,
            value
        }, {
            headers: {
                'X-Forwarded-For': ip
            },
            auth: {
                username,
                password
            },
        });

        console.log(`Status code: ${response.status}`);
        console.log(`Response data: ${JSON.stringify(response.data)}`);
    } catch (error) {
        console.error(error);
    }
}

const {
    Worker,
    isMainThread,
    parentPort
} = require('worker_threads');

if (isMainThread) {
    // Main thread code
    let isPaused = false;
    const workers = [];

    // Start worker threads and send them the functions to use for sending metric values
    for (let i = 0; i < 4; i++) {
        const worker = new Worker(__filename);
        worker.postMessage({
            functionIndex: i
        });
        workers.push(worker);
    }

    // Command listener to handle pause and resume commands
    process.stdin.setEncoding('utf8');
    process.stdin.on('data', (data) => {
        const command = data.toString().trim();

        if (command === 'pause') {
            isPaused = true;
            workers.forEach(worker => {
                worker.postMessage({
                    type: 'pause'
                });
            });
        } else if (command === 'resume') {
            isPaused = false;
            workers.forEach(worker => {
                worker.postMessage({
                    type: 'resume'
                });
            });
        }
    });
} else {
    // Worker thread code
    let isPaused = false;
    let shouldRun = true;


    parentPort.once('message', async (message) => {
        // Graph Functions
        const MAX_BATTERY_LEVEL = 100;

        function batteryLevel(t, factor) {
            return MAX_BATTERY_LEVEL * Math.pow(Math.E, -factor * 0.01 * t) * (1 - Math.sin(0.1 * t));
        }

        function humidityLevel(t, factor) {
            return 50 + 10 * Math.sin(0.01 * t) + Math.random() * 5 * factor - 2.5;;
        }

        function signalStrength(t, factor) {
            return 100 * Math.pow(Math.E, -0.05 * t) * (1 + 0.2 * Math.sin(0.01 * t)) + Math.random() * 5 * factor - 2.5;
        }

        function temperature(t, factor) {
            return 20 + 10 * Math.sin(0.01 * t) + 10 * Math.sin(0.005 * t) + Math.random() * 5 * factor - 2.5;
        }

        const functionIndex = message.functionIndex;

        const capeTownIP = '196.21.247.1';
        const johannesburgIP = '102.65.65.38';
        const durbanIP = '105.224.115.219';
        // agent
        const agentUsername = 'client-agent';
        const agentPassword = 'client-agent';
        const agentIP = capeTownIP;
        // admin
        const adminUsername = 'client';
        const adminPassword = 'client';
        const adminIP = johannesburgIP;
        // superuser
        const superuserUsername = 'tannar';
        const superuserPassword = 'moss';
        const superuserIP = durbanIP;

        const functionsToUse = [batteryLevel, humidityLevel, signalStrength, temperature];
        const username = [agentUsername, adminUsername, superuserUsername][functionIndex];
        const password = [agentPassword, adminPassword, superuserPassword][functionIndex];
        const ip = [agentIP, adminIP, superuserIP][functionIndex];

        while (shouldRun) {
            for (let t = 0; t <= 1; t += 0.001) {
                if (!isPaused) {
                    for (let i = 0; i < functionsToUse.length; i++) {
                        const metricFunction = functionsToUse[i];
                        const metricFunctionValue = metricFunction(t, i);
                        const functionToUseName = ['batteryLevel', 'humidityLevel', 'signalStrength', 'temperature'][i];
                        await insertMetric(`farm-location-${functionIndex + 1}`, functionToUseName, metricFunctionValue, 'tannar', 'moss', ip);
                    }
                }

                await new Promise(resolve => setTimeout(resolve, 50));
            }
        }
    });

    parentPort.once('message', (message) => {
        if (message.type === 'pause') {
            shouldRun = false;
        } else if (message.type === 'resume') {
            shouldRun = true;
        }
    });
}        