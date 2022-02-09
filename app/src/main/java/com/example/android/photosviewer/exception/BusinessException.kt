package com.example.android.photosviewer.exception

import com.example.android.photosviewer.data.model.app.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()