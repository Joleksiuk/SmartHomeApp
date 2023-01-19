import Button  from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import DeleteModal from './DeleteModal';
import { useEffect, useState } from 'react';
import {  Scene } from '../../interfaces';
import { Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import SceneService from '../../Services/SceneService';

export interface DeleteSceneProps{
    houseId?: number,
    scene?: Scene,
}
  
export default function DeleteScene(props:DeleteSceneProps) {

    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState<string>('');
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    const navigate = useNavigate();
    
    const onDeleteSceneClicked=()=>{
       if(props.scene?.id!==undefined && props.houseId!==undefined){
            setOpenDeleteModal(true);
            setDeleteMessage('Do you really want to delete scene: '+ props.scene.name+' from house?');
       }
    }

    const deleteScene=()=>{
        if(props.scene!=undefined)
            SceneService.deleteScene(props.scene?.id)
    }

    useEffect(() => {  
        if(props===undefined )
            return;    
        if(confirmDelete===true){
            setConfirmDelete(false);   
            navigate('/home/'+props.houseId)  
            deleteScene()
            
        }
    }, [confirmDelete]);

    return (
        <Grid container justifyContent="center" item xs={12}>  
            <DeleteModal setDeleteConfirm={setConfirmDelete} open={openDeleteModal} message={deleteMessage} setModalOpen={setOpenDeleteModal}/>
            <Button variant="outlined"  onClick = {onDeleteSceneClicked} startIcon={<DeleteIcon /> }>Delete scene</Button>
        </Grid>
    );
}