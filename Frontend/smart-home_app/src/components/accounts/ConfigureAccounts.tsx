import { useEffect, useState } from 'react'
import {Button, Grid, Typography} from '@mui/material';
import ShellyForm from './ShellyForm';
import TuyaForm from './TuyaForm';
import axios from 'axios';
import { shelly_users_url, tuya_users_url, } from '../../urls';
import AuthService from '../../authorization/AuthService';

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

      <Grid container direction="row" justifyContent="flex-end">  
          {shellyUserExists ?
          <div>
            <Typography>Shelly user already configured</Typography>
            <Button>Reset</Button>
          </div>
          :
            <ShellyForm/>
            }
          {tuyaUserExists ?
          <div>
            <Typography>Tuya user already configured</Typography>
            <Button>Reset</Button>
          </div>
          :
            <TuyaForm/> 
          }
             
      </Grid>
      );
}
