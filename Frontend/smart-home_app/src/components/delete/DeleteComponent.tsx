import Button  from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import DeleteModal from './DeleteModal';
import { useEffect, useState } from 'react';
import { Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { DeviceDto } from '../../interfaces';
import DeviceService from '../../Services/DeviceService';
import SceneService from '../../Services/SceneService';


export interface DeleteComponentProps{
    isSceneComponent: boolean,
    device?: DeviceDto,
    sceneId?: string,
}
  
export default function DeleteComponent(props:DeleteComponentProps) {

    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState<string>('');
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    const navigate = useNavigate();
    
    const deleteDeviceFromScene=()=>{
       if(props.sceneId!==undefined){
            setOpenDeleteModal(true);
            setDeleteMessage('Do you really want to delete device from scene?');
            navigate('/scene/'+props.sceneId)  
       }
    }

    const deleteDeviceFromHouse=()=>{
        setOpenDeleteModal(true);
        setDeleteMessage('Do you really want to delete device from house?');
    }
    
    useEffect(() => {  
        if(props===undefined )
            return;
        
        if(confirmDelete===true){
        if(props.device!==undefined && !props.isSceneComponent && props.sceneId===undefined){
            DeviceService.deleteDeviceFromHouse(props.device.houseId, props.device.id)
        }     
        else if(props.isSceneComponent && props.device!==undefined && props.sceneId!==undefined){
            SceneService.deleteDeviceFromScene(props.sceneId ,props.device.id);
        }
        setConfirmDelete(false);
        }
    }, [confirmDelete]);


    return (
        <Grid container justifyContent="center" item xs={12}>
            {!props.isSceneComponent &&  <Button variant="outlined" onClick = {deleteDeviceFromHouse} startIcon={<DeleteIcon />}>Delete device from house</Button>}
            {props.isSceneComponent  &&  <Button variant="outlined"  onClick = {deleteDeviceFromScene} startIcon={<DeleteIcon /> }>Delete device from scene</Button>}
            <DeleteModal setDeleteConfirm={setConfirmDelete} open={openDeleteModal} message={deleteMessage} setModalOpen={setOpenDeleteModal}/>
        </Grid>
    );
}