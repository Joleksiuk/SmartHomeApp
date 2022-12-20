import Card from '@mui/material/Card';
import axios from 'axios';
// import { TuyaContext  } from '@tuya/tuya-connector-nodejs';

// const context = new TuyaContext({
//     baseUrl: 'https://openapi.tuyaeu.com',
//     accessKey: 'cnqer8dptauykycwa8p4',
//     secretKey: 'b29b29c9b29a4f008d04b9f18e5283c1'
//   })

// export const main = async () => {

//     const device_id = "bf2b8148e20535ca2eaik5";

//      const devicedetail  = await context.device.detail({
//        device_id: device_id,
//      });
//      if(!devicedetail.success) {
//        new Error();
//      }
//      console.log("Device details:",devicedetail);

//      const commands = await context.request({
//        path: `/v1.0/iot-03/devices/${device_id}/commands`,
//        method: 'POST',
//        body: {
//          "commands":[{"code":"switch_led","value":true}]
//        }
//      });
//      if(!commands.success) {
//        new Error();
//      }
//      console.log("Execution result:",commands);
//    };
// main().catch(err => {
//     console.log(err);
// });