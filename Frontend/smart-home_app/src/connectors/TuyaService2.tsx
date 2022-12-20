import Card from '@mui/material/Card';

// const TuyAPI = require('tuyapi');

// export const device = new TuyAPI({
//   id: '7u5g78ek3yp4v7pfd735',
//   key: '8e7be48e8b4146089929474d30d0488f'});

// let stateHasChanged = false;

// device.find().then(() => {
//   device.connect();
// });

// device.on('connected', () => {
//   console.log('Connected to device!');
// });

// device.on('disconnected', () => {
//   console.log('Disconnected from device.');
// });

// device.on('error', (error: any) => {
//   console.log('Error!', error);
// });

// device.on('data', (data: { dps: { [x: string]: any; }; }) => {
//   console.log('Data from device:', data);

//   console.log(`Boolean status of default property: ${data.dps['1']}.`);

//   if (!stateHasChanged) {
//     device.set({set: !(data.dps['1'])});
//     stateHasChanged = true;
//   }
// });

// // Disconnect after 10 seconds
// setTimeout(() => { device.disconnect(); }, 10000);