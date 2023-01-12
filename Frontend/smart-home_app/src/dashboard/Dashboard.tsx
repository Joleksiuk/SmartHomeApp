import { Grid, Typography } from '@mui/material';

function DashboardContent() {

  return (
    <Grid>
      <Typography  variant="h6" >Welcome to Smart home dashboard!</Typography>
      <img src="https://img.freepik.com/free-vector/smart-home-flowchart_1284-32918.jpg?w=900&t=st=1672938347~exp=1672938947~hmac=f9972597ef45b7e1fd5cc9621f35a71eb737e4d66881a974c30d58d29c588bc7"/>
    </Grid>
  );
}

export default function Dashboard() {
  return <DashboardContent />;
}
