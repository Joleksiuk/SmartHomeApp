import {Alert,Box,Button,FormControl,FormControlLabel,FormLabel,Grid,List,ListItem, ListItemText, ListSubheader,Radio,RadioGroup,Stack,Switch,Table,TableBody,TableCell,TableRow,TextField,Typography,} from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import AuthService from "../authorization/AuthService";
import { Device, HouseUserDto, RolePermission, RolePermissionDto} from "../interfaces";
import { device_url, houseUser_url, permission_url } from "../urls";
  
export default function PermissionComponent() {
    
    const {id} = useParams()

    const [meAdmin, setMeAdmin]=useState<Boolean>(true);
    const [me, setMe] =useState<HouseUserDto>();
    const [devices, setDevices] = useState<Device[]>();
    const [permission, setPermission]=useState<RolePermissionDto[]>([]);
    const [see,setSee]=useState<Boolean>(false);

    const [devicesFetched, setDevicesFecthed]=useState<Boolean>(false)
    const [permsFetched, setPermsFetched]=useState<Boolean>(false)

    useEffect(() => {
        fetchHouse();   
        fetchDevices();
    }, []);
        
    useEffect(() => {   
        fetchPermissions()
    }, [devicesFetched]);

    useEffect(() => {   
        devices?.forEach(device=>getPermission(device.id))
    }, [permsFetched]);

    const fetchDevices = async () => {
        try {
          const response = await axios.get(device_url+'/houseId='+id);
          setDevices(response.data);
          setDevicesFecthed(true);
        } catch (error) {
          console.error(error);
        }
      };

    const fetchPermissions = async () => {
        try {
            //const response = await axios.get(device_url+'/houseId='+id);
            devices?.forEach(device=>getPermission(device.id))
            setPermsFetched(true)
        } catch (error) {
            console.error(error);
        }
    };

    const fetchHouse = async () => {
        try {
          const response = await  axios.get<HouseUserDto>(houseUser_url+"/userId="+AuthService.getLoggedUser().id +'/houseId=' + id);
          setMe(response.data);
            if(response.data.role==='Admin'){
                setMeAdmin(true)
            }
        } catch (error) {
          console.error(error);
        }
      };



    // useEffect(() => {

    //     axios.get<HouseUserDto>(houseUser_url+"/userId="+AuthService.getLoggedUser().id +'/houseId=' + id)
    //     .then((response) => {
    //         setMe(response.data);
    //         if(response.data.role==='Admin'){
    //             setMeAdmin(true)
    //         }})

    //     axios.get<Device[]>(device_url+'/houseId='+id,{})
    //     .then((response)=> {        
    //         setDevices(response.data)
    //      })
    //     .then((response)=> {         
            
    //      })
    //     .catch((error) => console.log(error));

    // }, []);

    // useEffect(()=>{
    //     devices?.forEach(device=>getPermission(device.id))
    //     console.log(permission)
    // },[devices])

    const getPermission=(deviceId: number)=>{
  
        axios.get<RolePermissionDto[]>(permission_url+'/'+deviceId,{})
        .then((response)=> {
            permission?.concat(response.data)
            setSee(true)
        })
        .catch((error) => console.log(error));
    }

    const setNewPermission=(event:any, value:any, rp:RolePermission, role:string)=>{
            
        let canSee:string="true";
        let canControl:string="false";

        switch(value){
            case 'See':
                canSee='true'
                canControl='false'
                break;
            case 'Control':
                canSee='true'
                canControl='true'
                break;
        }

        let msg={
            'id':rp.id,
            'deviceId':rp.deviceId,
            'role':role,
            'canSee':canSee,
            'canControl':canControl
        }

        axios.put(permission_url,msg)
        .then((response) => {console.log(response.data)})
        .catch((error) => console.log(error));
    }

    const generateRadio=(device:Device, role:string)=>{
        let rp={
            'id':0,
            'deviceName':device.name,
            'deviceId':device.id,
            'role':role,
            'canSee':"true",
            'canControl':"true"
        };
        let de = "See"

        if(permission!==undefined)
        {
            for (const per of permission) {
                if(per.deviceId===device.id){
                    if(per.role===role){
                        rp=per;  
                        if(per.canControl=='true'){
                            de="Control"
                        }
                    }
                }
            }   
        };       
        if(meAdmin){
            return (
                <FormControl>
                        <RadioGroup
                            aria-labelledby="demo-radio-buttons-group-label"
                            value={de}
                            name="radio-buttons-group"
                            onChange={(e,value) => setNewPermission(e,value,rp,role)}
                        >
                        <FormControlLabel value="See" control={<Radio />} label="See" />
                        <FormControlLabel value="Control" control={<Radio />} label="Control" />
                    </RadioGroup>
                </FormControl>
            )
        }
        else{
            return (<Typography>{} </Typography>)
        }                
    }

    const generateTableRow = (device:Device) => {   
        return (       
            <TableRow key={device.id}>
                <TableCell>{device.name}</TableCell>
                <TableCell>{generateRadio(device,"Guest")}</TableCell>
                <TableCell>{generateRadio(device,"Resident")}</TableCell>
            </TableRow>
        )              
    }

    const seeContent=()=>{

    }
    
    return (
    <div>
        {(devicesFetched && permsFetched) &&    
            <Grid container spacing={3}>
                <Grid justifyContent="center" container item xs={12}>
                    <Typography  variant="h6" >Set roles permission</Typography>
    
                </Grid>    
    
                <Grid justifyContent="center" container item xs={12}>
                    <Table>
                    
                        <TableBody>
                            <TableRow>
                                <TableCell><Typography>Device</Typography></TableCell>
                                <TableCell><Typography>Guest</Typography></TableCell>
                                <TableCell><Typography>Resident</Typography></TableCell>
                            </TableRow>
                            {devices?.map(device => (
                                generateTableRow(device)
                            ))}
                        </TableBody>
                    </Table>
                </Grid>
    
            </Grid>}
    </div>
       
    
    );  
};
