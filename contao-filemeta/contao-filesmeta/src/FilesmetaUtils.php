<?php

namespace XProjects\Filesmeta;

class FilesmetaUtils extends \Frontend {

  public static function getMeta($id) {
    $meta = array(
        'filesmetatext' => '',
        'filesmetaimage' => ''
    );
    if ($id != 0 && $id != "") {
      $fileData = \FilesModel::findById($id);
      if ($fileData !== null) {
        $meta['filesmetatext'] = $fileData->filesmetatext;
        $imageSize = array();
        if ($fileData->filesmetaimagesize != '') {
          $imageSize = deserialize($fileData->filesmetaimagesize);
        }
        $image = \FilesModel::findByUuid($fileData->filesmetaimage);
        if ($image !== null) {
          $fileCheck = new \File($image->path, true);
          if ($fileCheck->exists()) {
            if (count($imageSize) >= 3) {
              $meta['filesmetaimage'] = \Image::get($image->path, $imageSize[0], $imageSize[1], $imageSize[2]);
            } else {
              $meta['filesmetaimage'] = \Image::get($image->path, $fileCheck->width, $fileCheck->height);
            }
          }
        }
      }
    }
    return $meta;
  }

}
