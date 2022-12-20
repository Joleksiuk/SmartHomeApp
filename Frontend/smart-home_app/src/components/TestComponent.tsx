import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Button, CardActionArea, Grid, Paper } from '@mui/material';
import ShellyController from '../connectors/ShellyController';
//import {main} from '../connectors/TuyaService4'
export default function TestComponent() {

   const toggleLight =()=>{
      ShellyController.toggleLight()
   }

   const test1=async ()=>{
      // main();
   }
   const test2=()=>{
    const crypto = require('crypto');  
    const secret = 'abcdefg';  
    const hash = crypto.createHmac('sha256', secret)  
                       .update('Welcome to JavaTpoint')  
                       .digest('hex');  
    console.log(hash);  
   }

    return (
      <Paper
        sx={{
        p: 2,
        display: 'flex',
        flexDirection: 'column',
      }}>
          <Grid container spacing={3}>
              <Grid item xs={12} md={8} lg={25}>
              <Button onClick = {test1}>test1</Button>
              <Button onClick = {test2}>Test2</Button>
              <Button onClick = {toggleLight}>Toggle Light</Button>
              </Grid>
          </Grid>
        </Paper>
      );
}