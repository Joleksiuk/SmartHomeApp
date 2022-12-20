import { tuyaApi } from 'tuya-cloud-api';
 
const apiClientId = '7u5g78ek3yp4v7pfd735';
const apiClientSecret = '8e7be48e8b4146089929474d30d0488f';
const deviceIde = 'bf2b8148e20535ca2eaik5';
const code = 'switch_led';
 
export async function toggleDevice(deviceId: string, state = false) {
  await tuyaApi.authorize({
    apiClientId,
    apiClientSecret,
    serverLocation: 'eu',
  });
 
  const deviceStatus = await tuyaApi.getDeviceStatus({
    deviceId: deviceIde,
  });
  const switchStatus = deviceStatus.find((item) => item.code === code);
 
  if (!switchStatus) {
    throw new Error(`Can not find status for command: ${code}`);
  }
 
  if (switchStatus.value === state) {
    return;
  }
 
  await tuyaApi.sendCommand({
    deviceId,
    commands: [
      {
        code,
        value: state,
      },
    ],
  });
}
 
{
  (async () => {
    try {
      await toggleDevice(deviceIde, true);
 
      console.log('Successfully toggled the device on!');
      process.exit(0);
    } catch (error) {
      console.error('Error toggling device on', error);
      process.exit(1);
    }
  })();
}