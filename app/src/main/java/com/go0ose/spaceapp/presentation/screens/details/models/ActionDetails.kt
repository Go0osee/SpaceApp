package com.go0ose.spaceapp.presentation.screens.details.models

sealed class ActionDetails {
   object OnClickTutorial: ActionDetails()
   object OnClickButtonBack: ActionDetails()
   object OnClickButtonShare: ActionDetails()
}