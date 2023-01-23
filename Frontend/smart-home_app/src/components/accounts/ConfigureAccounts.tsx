import { useEffect, useState } from 'react'
import {Box, Button, Grid, Paper, Typography} from '@mui/material';
import ShellyForm from './ShellyForm';
import TuyaForm from './TuyaForm';
import axios from 'axios';
import { shelly_users_url, tuya_users_url, } from '../../urls';
import AuthService from '../../authorization/AuthService';
import CheckIcon from '@mui/icons-material/Check';
import DeleteTuyaUser from '../delete/DeleteTuyaUser';
import DeleteShellyUser from '../delete/DeleteShellyUser';

export default function ConfigureAccounts() {

    const [shellyUserExists, setShellyUserExists]=useState<boolean>(false)
    const [tuyaUserExists, setTuyaUserExists]=useState<boolean>(false)
    
    useEffect(() => {

      axios.get(shelly_users_url+'/'+AuthService.getLoggedUser().id.toString(), {})
      .then(response => { if (response.data) setShellyUserExists(true)})

      axios.get(tuya_users_url+'/'+AuthService.getLoggedUser().id.toString(), {})
      .then(response => { if (response.data) setTuyaUserExists(true)})
    
      }, []);


    return (
      <Grid container direction="row" justifyContent="center">  
        <Grid xs={6} container justifyContent="center">
        <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
          >
          {shellyUserExists ?      
            
            <Grid container direction="column" justifyContent="center">
              <img width="200" height="100"  src='https://i.postimg.cc/4dJPTCr0/shelly-logo.png'/>
              <Typography>Shelly user already configured</Typography>
              <DeleteShellyUser userId={AuthService.getLoggedUser().id}/>
            </Grid>  
          :
          <ShellyForm/>
            }
            </Box>
        </Grid>
        <Grid xs={6} container justifyContent="center">
        <Box
             display="flex"
             justifyContent="center"
             alignItems="center"
           >
          {tuyaUserExists ?
            
            <Grid container direction="column" justifyContent="center">
              <img width="200" height="100"  src='https://i.postimg.cc/htBp072g/tuya-logo.png'/>
              <Typography>Tuya user already configured</Typography>
              <DeleteTuyaUser userId={AuthService.getLoggedUser().id}/>
            </Grid> 
            :
            <TuyaForm/>
          }
          </Box>
        </Grid>
      </Grid>
      );
}
