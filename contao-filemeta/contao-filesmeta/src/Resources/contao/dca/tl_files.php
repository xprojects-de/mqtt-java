<?php

$GLOBALS['TL_DCA']['tl_files']['palettes']['default'] = str_replace('meta', 'meta;filesmetaimage,filesmetaimagesize,filesmetatext', $GLOBALS['TL_DCA']['tl_files']['palettes']['default']);

$GLOBALS['TL_DCA']['tl_files']['fields']['filesmetaimage'] = array
    (
    'label' => &$GLOBALS['TL_LANG']['tl_files']['filesmetaimage'],
    'inputType' => 'fileTree',
    'eval' => array('filesOnly' => true, 'fieldType' => 'radio', 'mandatory' => false, 'tl_class' => 'clr'),
    'sql' => "binary(16) NULL"
);
$GLOBALS['TL_DCA']['tl_files']['fields']['filesmetaimagesize'] = array
    (
    'label' => &$GLOBALS['TL_LANG']['tl_files']['filesmetaimagesize'],
    'inputType' => 'imageSize',
    'options' => $GLOBALS['TL_CROP'],
    'reference' => &$GLOBALS['TL_LANG']['MSC'],
    'eval' => array('rgxp' => 'digit', 'nospace' => true, 'helpwizard' => true, 'tl_class' => 'w50', 'mandatory' => false, 'includeBlankOption' => true),
    'sql' => "varchar(64) NOT NULL default ''"
);
$GLOBALS['TL_DCA']['tl_files']['fields']['filesmetatext'] = array
    (
    'label' => &$GLOBALS['TL_LANG']['tl_files']['filesmetatext'],
    'exclude' => true,
    'inputType' => 'textarea',
    'eval' => array('rte' => 'tinyMCE', 'tl_class' => 'clr', 'mandatory' => false),
    'sql' => "mediumtext NULL"
);
