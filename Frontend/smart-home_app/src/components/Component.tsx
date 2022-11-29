import React from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Button, CardActionArea } from '@mui/material';
import ShellyController from '../connectors/ShellyController';

export default function component() {

   const toggleLight =()=>{
      ShellyController.toggleLight()
   }
    return (
      <Button onClick = {toggleLight}>
        <Card sx={{ maxWidth: 345 }}>
          <CardActionArea>
            <CardMedia
              component="img"
              height="300"
              image="https://www.houseiq.pl/userdata/public/gfx/13281.jpg"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                Iot Component
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Iot Component description.
              </Typography>
              <Button>
                Details
              </Button>
            </CardContent>
          </CardActionArea>
        </Card>
      </Button>
      );
}
