import React from 'react'
import { Button, Grid, Paper, Typography } from '@mui/material';
import ShellyForm from './ShellyForm';
import TuyaForm from './TuyaForm';
import AuthService from '../../authorization/AuthService';


export default function component() {

    return (

      <Grid container direction="row" justifyContent="flex-end">  
          <ShellyForm/>
          <TuyaForm/>    
      </Grid>
      );
}
