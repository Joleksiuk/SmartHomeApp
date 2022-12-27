import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Button, CardActionArea, Grid, Paper } from '@mui/material';
import ShellyController from '../connectors/ShellyController';

export default function TestComponent() {

   const toggleLight =()=>{
      ShellyController.toggleLight()
   }
   const test1=()=>{
  }
   const test2=()=>{
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